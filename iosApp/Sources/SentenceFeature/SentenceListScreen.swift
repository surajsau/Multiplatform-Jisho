//
//  File.swift
//  
//
//  Created by lease-emp-mac-suraj-sau on 2022/12/11.
//

import SwiftUI
import shared
import Utils

public struct SentenceListScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    
    @ObservedObject private var nativeVM = NativeViewModel(viewModel: SentenceListViewModel.init())
    
    private let word: String
    
    public init(word: String) {
        self.word = word
    }
    
    public var body: some View {
        ZStack {
            VStack {
                if let state = nativeVM.state {
                    ScrollView(.vertical) {
                        LazyVStack {
                            ForEach(state.sentences, id: \.self) { sentence in
                                NavigationLink {
                                    SentenceDetailsScreen(id: sentence.id)
                                } label: {
                                    SentenceRow(sentence: sentence)
                                        .padding(.horizontal)
                                        .padding(.bottom, 8)
                                }
                            }
                        }
                        .padding(.vertical)
                    }
                }
                
                Spacer()
            }
        }
        .onAppear {
            nativeVM.viewModel.doInitWith(word: word)
        }
    }
    
}
